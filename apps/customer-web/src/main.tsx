import React from 'react';
import ReactDOM from 'react-dom/client';
import { App } from './app';
import { CheckoutPage } from './checkout';
import './styles.css';

const path = window.location.pathname;

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    {path.includes('/checkout') ? <CheckoutPage /> : <App />}
  </React.StrictMode>,
);
