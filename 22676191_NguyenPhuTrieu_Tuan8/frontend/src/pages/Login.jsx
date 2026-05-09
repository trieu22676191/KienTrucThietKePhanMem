import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { api } from '../api';

const Login = ({ setAuthUser }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    try {
      const data = await api.login(username, password);
      localStorage.setItem('token', data.token);
      setAuthUser(data.username);
      navigate('/'); // Go back to home
    } catch (err) {
      setError('Invalid username or password');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="cart-layout" style={{ display: 'flex', justifyContent: 'center', marginTop: '4rem' }}>
      <form onSubmit={handleLogin} className="cart-items" style={{ width: '100%', maxWidth: '400px', padding: '2rem' }}>
        <h2 style={{ marginBottom: '1.5rem', textAlign: 'center' }}>Welcome Back</h2>
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
          {loading ? 'Logging in...' : 'Login'}
        </button>
        
        <div style={{ marginTop: '1rem', textAlign: 'center', color: 'var(--text-muted)' }}>
          Don't have an account? <Link to="/register" style={{ color: 'var(--primary)' }}>Register</Link>
        </div>
      </form>
    </div>
  );
};

export default Login;
