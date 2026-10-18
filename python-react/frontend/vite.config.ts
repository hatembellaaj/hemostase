import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  preview: { proxy: { '/api': 'http://localhost:8000' } },
  server: {
    port: 5173,
    proxy: { '/api': process.env.VITE_API_PROXY ?? 'http://localhost:8000' },
  },
});
