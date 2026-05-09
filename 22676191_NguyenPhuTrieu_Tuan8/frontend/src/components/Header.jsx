import React from 'react';
import { Link } from 'react-router-dom';
import { ShoppingCart, Zap, User, LogOut } from 'lucide-react';

const Header = ({ cartCount, authUser, setAuthUser }) => {
  const handleLogout = () => {
    localStorage.removeItem('token');
    setAuthUser(null);
  };

  return (
    <header className="header">
      <div className="header-container">
        <Link to="/" className="logo">
          <Zap size={28} color="#ec4899" fill="#ec4899" />
          FlashSale
        </Link>
        <nav className="nav-links">
          <Link to="/" style={{ fontWeight: 500 }}>Products</Link>
          <Link to="/cart" className="cart-link">
            <ShoppingCart size={20} />
            <span>Cart</span>
            {cartCount > 0 && (
              <span className="cart-badge">{cartCount}</span>
            )}
          </Link>
          
          {authUser ? (
            <div style={{ display: 'flex', alignItems: 'center', gap: '1rem', marginLeft: '1rem', paddingLeft: '1rem', borderLeft: '1px solid var(--border)' }}>
              <span style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', color: 'var(--primary)' }}>
                <User size={18} /> {authUser}
              </span>
              <button onClick={handleLogout} className="btn" style={{ padding: '0.5rem', width: 'auto', background: 'transparent' }}>
                <LogOut size={18} color="var(--text-muted)" />
              </button>
            </div>
          ) : (
            <Link to="/login" className="btn btn-primary" style={{ width: 'auto', padding: '0.5rem 1.5rem', marginLeft: '1rem' }}>
              Login
            </Link>
          )}
        </nav>
      </div>
    </header>
  );
};

export default Header;
