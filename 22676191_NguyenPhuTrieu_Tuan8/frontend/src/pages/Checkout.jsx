import React, { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { api } from '../api';
import { CreditCard, CheckCircle2 } from 'lucide-react';

const Checkout = ({ updateCartCount }) => {
  const navigate = useNavigate();
  const [cart, setCart] = useState(null);
  const [loading, setLoading] = useState(true);
  const [processing, setProcessing] = useState(false);
  const [success, setSuccess] = useState(false);
  const [orderId, setOrderId] = useState(null);

  const [formData, setFormData] = useState({
    name: '',
    address: '',
    phone: '',
    email: ''
  });

  useEffect(() => {
    const fetchCart = async () => {
      try {
        const data = await api.getCart();
        if (!data.items || data.items.length === 0) {
          navigate('/cart');
        } else {
          setCart(data);
        }
      } catch (error) {
        console.error('Failed to fetch cart for checkout', error);
      } finally {
        setLoading(false);
      }
    };
    fetchCart();
  }, [navigate]);

  const handleInputChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleCheckout = async (e) => {
    e.preventDefault();
    setProcessing(true);
    try {
      const result = await api.checkout({
        userInfo: formData,
        cart: cart
      });
      setOrderId(result.orderId);
      setSuccess(true);
      updateCartCount(); // Reset cart count to 0
    } catch (error) {
      console.error('Checkout failed', error);
      alert('Checkout failed. Please try again.');
    } finally {
      setProcessing(false);
    }
  };

  if (loading) return <div className="loading"><div className="spinner"></div></div>;

  const calculatedTotal = cart?.total || cart?.items?.reduce((acc, item) => acc + ((item.price || 0) * (item.quantity || 1)), 0) || 0;

  if (success) {
    return (
      <div className="empty-state" style={{ maxWidth: '600px', margin: '0 auto' }}>
        <CheckCircle2 color="var(--success)" size={64} style={{ marginBottom: '1rem' }} />
        <h2 style={{ color: 'var(--success)', marginBottom: '1rem' }}>Order Confirmed!</h2>
        <p style={{ color: 'var(--text-muted)', marginBottom: '2rem' }}>
          Your flash sale order has been placed successfully. 
          <br /><br />
          Order ID: <strong>{orderId}</strong>
        </p>
        <Link to="/" className="btn btn-primary" style={{ width: 'auto' }}>Continue Shopping</Link>
      </div>
    );
  }

  return (
    <div>
      <h1 className="page-title">Checkout</h1>
      <div className="cart-layout">
        <form onSubmit={handleCheckout} className="cart-items" style={{ padding: '2rem' }}>
          <h2 style={{ marginBottom: '1.5rem' }}>Shipping Information</h2>
          
          <div className="form-group">
            <label className="form-label">Full Name</label>
            <input 
              required
              name="name"
              type="text" 
              className="form-input" 
              placeholder="John Doe"
              value={formData.name}
              onChange={handleInputChange}
            />
          </div>
          
          <div className="form-group">
            <label className="form-label">Email</label>
            <input 
              required
              name="email"
              type="email" 
              className="form-input" 
              placeholder="john@example.com"
              value={formData.email}
              onChange={handleInputChange}
            />
          </div>
          
          <div className="form-group">
            <label className="form-label">Phone</label>
            <input 
              required
              name="phone"
              type="tel" 
              className="form-input" 
              placeholder="+1 (555) 000-0000"
              value={formData.phone}
              onChange={handleInputChange}
            />
          </div>
          
          <div className="form-group">
            <label className="form-label">Address</label>
            <textarea 
              required
              name="address"
              className="form-input" 
              rows="3" 
              placeholder="123 Main St, City, Country"
              value={formData.address}
              onChange={handleInputChange}
            ></textarea>
          </div>
          
          <button 
            type="submit" 
            className="btn btn-primary" 
            disabled={processing}
            style={{ marginTop: '1rem' }}
          >
            {processing ? (
              <><div className="spinner" style={{ width: '20px', height: '20px', borderWidth: '2px' }}></div> Processing...</>
            ) : (
              <><CreditCard /> Confirm Order</>
            )}
          </button>
        </form>
        
        <div className="cart-summary">
          <h3 style={{ fontSize: '1.25rem', marginBottom: '1.5rem' }}>Order Summary</h3>
          {cart?.items.map((item, idx) => (
            <div key={idx} style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '0.5rem', fontSize: '0.875rem', color: 'var(--text-muted)' }}>
              <span>{item.quantity}x {item.name}</span>
              <span>${((item.price || 0) * (item.quantity || 1)).toLocaleString()}</span>
            </div>
          ))}
          <div className="summary-total" style={{ marginTop: '1.5rem' }}>
            <span>Total</span>
            <span>${calculatedTotal.toLocaleString()}</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
