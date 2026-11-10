import React from 'react';

const summaryItems = [
  { name: 'Pav Bhaji', quantity: 1, price: 140 },
  { name: 'Paneer Momos', quantity: 1, price: 160 },
];

export function CheckoutPage() {
  const subtotal = summaryItems.reduce((sum, item) => sum + item.price * item.quantity, 0);
  const deliveryFee = 30;
  const total = subtotal + deliveryFee;

  return (
    <main style={{ maxWidth: 960, margin: '0 auto', padding: 32, color: '#21170f' }}>
      <h1>Checkout</h1>
      <p style={{ color: '#6b625b' }}>Review your delivery details and order before placing it.</p>
      <div style={{ display: 'grid', gridTemplateColumns: 'minmax(0, 1.4fr) minmax(280px, 1fr)', gap: 24 }}>
        <section style={{ background: '#fff', padding: 24, borderRadius: 16, border: '1px solid #f0e2d7' }}>
          <h2>Delivery details</h2>
          <strong>Home</strong>
          <p>42, Colaba Causeway, Mumbai</p>
          <p>Estimated delivery: 30–40 minutes</p>
          <h3>Payment method</h3>
          <label>
            <input type="radio" name="payment" defaultChecked /> Cash on Delivery
          </label>
        </section>
        <aside style={{ background: '#fff', padding: 24, borderRadius: 16, border: '1px solid #f0e2d7' }}>
          <h2>Order summary</h2>
          {summaryItems.map((item) => (
            <div key={item.name} style={{ display: 'flex', justifyContent: 'space-between', gap: 12, margin: '12px 0' }}>
              <span>{item.name} × {item.quantity}</span>
              <strong>₹{item.price * item.quantity}</strong>
            </div>
          ))}
          <hr />
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            <span>Subtotal</span><span>₹{subtotal}</span>
          </div>
          <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: 8 }}>
            <span>Delivery fee</span><span>₹{deliveryFee}</span>
          </div>
          <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: 16, fontSize: 20 }}>
            <strong>Total</strong><strong>₹{total}</strong>
          </div>
          <button type="button" style={{ width: '100%', marginTop: 20, border: 0, borderRadius: 10, padding: 14, background: '#e87518', color: '#fff', fontWeight: 700 }}>
            Place demo order
          </button>
          <p style={{ color: '#766a60', fontSize: 12 }}>Demo checkout only. Payment and order placement are not connected to live services.</p>
        </aside>
      </div>
    </main>
  );
}
