import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { api } from '../api';
import { ShoppingBag, ArrowRight } from 'lucide-react';

const Cart = () => {
  const [cart, setCart] = useState({ items: [], total: 0 });
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCart = async () => {
      try {
        const data = await api.getCart();
        setCart(data);
      } catch (error) {
        console.error('Failed to fetch cart', error);
      } finally {
        setLoading(false);
      }
    };
    fetchCart();
  }, []);

  if (loading) return <div className="loading"><div className="spinner"></div></div>;

  if (!cart.items || cart.items.length === 0) {
    return (
      <div className="empty-state">
        <ShoppingBag className="empty-icon" />
        <h2>Your Cart is Empty</h2>
        <p style={{ color: 'var(--text-muted)', marginBottom: '2rem' }}>Looks like you haven't added any flash deals yet.</p>
        <Link to="/" className="btn btn-primary" style={{ width: 'auto' }}>Start Shopping</Link>
      </div>
    );
  }

  const calculatedTotal = cart.total || cart.items.reduce((acc, item) => acc + ((item.price || 0) * (item.quantity || 1)), 0);

  return (
    <div>
      <h1 className="page-title">Shopping Cart</h1>
      <div className="cart-layout">
        <div className="cart-items">
          {cart.items.map((item, index) => (
            <div key={`${item.productId}-${index}`} className="cart-item">
              <img src={item.image} alt={item.name} className="cart-item-img" />
              <div className="cart-item-info">
                <h3 className="cart-item-title">{item.name}</h3>
                <div style={{ color: 'var(--text-muted)' }}>Quantity: {item.quantity}</div>
              </div>
              <div style={{ fontSize: '1.25rem', fontWeight: 600 }}>
                ${(item.price * item.quantity).toLocaleString()}
              </div>
            </div>
          ))}
        </div>
        
        <div className="cart-summary">
          <h3 style={{ fontSize: '1.25rem', marginBottom: '1.5rem' }}>Order Summary</h3>
          <div className="summary-row">
            <span>Subtotal</span>
            <span>${calculatedTotal.toLocaleString()}</span>
          </div>
          <div className="summary-row">
            <span>Shipping</span>
            <span style={{ color: 'var(--success)' }}>Free</span>
          </div>
          <div className="summary-row summary-total">
            <span>Total</span>
            <span>${calculatedTotal.toLocaleString()}</span>
          </div>
          
          <button 
            className="btn btn-primary" 
            style={{ marginTop: '2rem' }}
            onClick={() => navigate('/checkout')}
          >
            Proceed to Checkout <ArrowRight size={20} />
          </button>
        </div>
      </div>
    </div>
  );
};

export default Cart;
