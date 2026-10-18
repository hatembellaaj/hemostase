const TOKEN_KEY = 'hemostase-token';

export function getToken(): string | null {
  try {
    return localStorage.getItem(TOKEN_KEY) ?? sessionStorage.getItem(TOKEN_KEY);
  } catch {
    return null;
  }
}

export function setToken(token: string | null, remember = false) {
  try {
    localStorage.removeItem(TOKEN_KEY);
    sessionStorage.removeItem(TOKEN_KEY);
    if (token) (remember ? localStorage : sessionStorage).setItem(TOKEN_KEY, token);
  } catch {
    /* stockage indisponible */
  }
}

export class ApiError extends Error {
  constructor(
    public status: number,
    message: string,
    public fieldErrors: Record<string, string> = {},
  ) {
    super(message);
  }
}

let onUnauthorized: () => void = () => {};
export function setUnauthorizedHandler(fn: () => void) {
  onUnauthorized = fn;
}

export async function api<T = unknown>(path: string, init: RequestInit = {}): Promise<{ data: T; headers: Headers }> {
  const headers = new Headers(init.headers);
  const token = getToken();
  if (token) headers.set('Authorization', `Bearer ${token}`);
  if (init.body && !headers.has('Content-Type')) headers.set('Content-Type', 'application/json');
  const res = await fetch(path, { ...init, headers });
  if (res.status === 401 && !path.endsWith('/authenticate')) onUnauthorized();
  if (!res.ok) {
    let msg = `Erreur ${res.status}`;
    let fieldErrors = {};
    try {
      const body = await res.json();
      const d = body.detail;
      if (typeof d === 'string') msg = d;
      else if (d?.message) {
        msg = d.message;
        fieldErrors = d.fieldErrors ?? {};
      }
    } catch {
      /* corps vide */
    }
    throw new ApiError(res.status, msg, fieldErrors);
  }
  const data = res.status === 204 ? (undefined as T) : ((await res.json()) as T);
  return { data, headers: res.headers };
}

export async function download(path: string, filename: string) {
  const res = await fetch(path, { headers: { Authorization: `Bearer ${getToken()}` } });
  if (!res.ok) throw new ApiError(res.status, 'Export impossible');
  const blob = await res.blob();
  const url = URL.createObjectURL(blob);
  const a = document.createElement('a');
  a.href = url;
  a.download = filename;
  a.click();
  URL.revokeObjectURL(url);
}
