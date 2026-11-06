import React from 'react';
import ReactDOM from 'react-dom/client';
import { orders } from './orders';
import './styles.css';

function App() {
  return (
    <div className="admin-shell">
      <header className="topbar">
        <div>
          <p className="eyebrow">Restaurant control</p>
          <h1>Pada Pav Kitchen Dashboard</h1>
        </div>
        <button className="primary-btn">Sync Orders</button>
      </header>

      <section className="stats">
        <div><span>Open Orders</span><strong>12</strong></div>
        <div><span>Preparing</span><strong>4</strong></div>
        <div><span>Ready</span><strong>3</strong></div>
        <div><span>Deliveries</span><strong>7</strong></div>
      </section>

      <main className="table-wrap">
        <table>
          <thead>
            <tr>
              <th>Order</th>
              <th>Customer</th>
              <th>Status</th>
              <th>Items</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {orders.map((order) => (
              <tr key={order.id}>
                <td>{order.id}</td>
                <td>{order.customer}</td>
                <td><span className="status-pill">{order.status}</span></td>
                <td>{order.items}</td>
                <td><button>Review</button></td>
              </tr>
            ))}
          </tbody>
        </table>
      </main>
    </div>
  );
}

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
);
