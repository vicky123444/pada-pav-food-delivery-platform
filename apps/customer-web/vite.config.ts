import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default config({
  plugins: [react()],
  server: {
    port: 3000,
  },
});
