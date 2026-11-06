import React from 'react';
import './styles.css';

const menu = [
  { name: 'Pav Bhaji', price: 140 },
  { name: 'Pada Pav', price: 70 },
  { name: 'Grilled Cheese Sandwich', price: 120 },
  { name: 'Paneer Momos', price: 160 },
  { name: 'Mumbai Chaap', price: 180 },
];

export function App() {
  return (
    <div className="app-shell">
      <header className="hero">
        <div>
          <p className="eyebrow">Mumbai street food</p>
          <h1>Pada Pav</h1>
        </div>
        <button className="primary-btn">Login</button>
      </header>

      <section className="categories">
        <span>Popular</span>
        <span>Snacks</span>
        <span>Combo meals</span>
        <span>Veg</span>
      </section>

      <main className="menu-grid">
        {menu.map((item) => (
          <article className="menu-card" key={item.name}>
            <div className="badge">Chef special</div>
            <h3>{item.name}</h3>
            <p>Freshly prepared with authentic Mumbai flavours.</p>
            <div className="card-footer">
              <strong>₹{item.price}</strong>
              <button>Add</button>
            </div>
          </article>
        ))}
      </main>
    </div>
  );
}
