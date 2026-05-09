import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { api } from '../api';

const Register = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState(false);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    try {
      await api.register(username, password);
      setSuccess(true);
      setTimeout(() => navigate('/login'), 2000);
    } catch (err) {
      setError(err.response?.data || 'Failed to register');
    } finally {
      setLoading(false);
    }
  };

  if (success) {
    return (
      <div className="empty-state" style={{ marginTop: '4rem', maxWidth: '400px', margin: '4rem auto' }}>
        <h2 style={{ color: 'var(--success)', marginBottom: '1rem' }}>Registration Successful!</h2>
        <p>Redirecting to login...</p>
      </div>
    );
  }

  return (
    <div className="cart-layout" style={{ display: 'flex', justifyContent: 'center', marginTop: '4rem' }}>
      <form onSubmit={handleRegister} className="cart-items" style={{ width: '100%', maxWidth: '400px', padding: '2rem' }}>
        <h2 style={{ marginBottom: '1.5rem', textAlign: 'center' }}>Create Account</h2>
        {error && <div style={{ color: 'var(--accent)', marginBottom: '1rem', textAlign: 'center' }}>{error}</div>}
        
        <div className="form-group">
          <label className="form-label">Username</label>
          <input required type="text" className="form-input" value={username} onChange={e => setUsername(e.target.value)} />
        </div>
        
        <div className="form-group">
          <label className="form-label">Password</label>
          <input required type="password" className="form-input" value={password} onChange={e => setPassword(e.target.value)} />
        </div>
        
        <button type="submit" className="btn btn-primary" disabled={loading}>
          {loading ? 'Registering...' : 'Register'}
        </button>
        
        <div style={{ marginTop: '1rem', textAlign: 'center', color: 'var(--text-muted)' }}>
          Already have an account? <Link to="/login" style={{ color: 'var(--primary)' }}>Login</Link>
        </div>
      </form>
    </div>
  );
};

export default Register;
