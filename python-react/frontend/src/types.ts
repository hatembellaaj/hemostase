export type Condition =
  | { field: string; op: 'eq' | 'ne' | 'gt' | 'gte' | 'lt' | 'lte'; value: unknown }
  | { field: string; op: 'truthy' | 'falsy' | 'filled' }
  | { all: Condition[] }
  | { any: Condition[] };

export interface FieldDef {
  name: string;
  column: string;
  type: 'string' | 'enum' | 'integer' | 'float' | 'boolean' | 'date' | 'datetime' | 'text';
  label: string;
  section: string;
  required: boolean;
  maxLength?: number;
  min?: number;
  max?: number;
  options?: { value: string; label: string }[];
  widget?: 'radio' | 'multicheck' | 'textarea';
  choices?: string[];
  visibleIf?: Condition;
}

export interface Computed {
  field: string;
  fn: 'age' | 'ratio';
  args: string[];
}

export interface EntityDef {
  name: string;
  slug: string;
  title: string;
  table: string;
  sections: string[];
  listColumns: string[];
  computed: Computed[];
  fields: FieldDef[];
}

export type Fiche = Record<string, unknown> & { id?: number };

export interface Account {
  id: number;
  login: string;
  firstName: string | null;
  lastName: string | null;
  email: string | null;
  authorities: string[];
}
