import React, { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import ProductList from './pages/ProductList';
import ProductDetail from './pages/ProductDetail';
import Cart from './pages/Cart';
import Checkout from './pages/Checkout';
import Login from './pages/Login';
import Register from './pages/Register';
import { api } from './api';

function App() {
  const [cartCount, setCartCount] = useState(0);
  const [authUser, setAuthUser] = useState(null);

  const fetchCartCount = async () => {
    try {
      const cart = await api.getCart();
      const count = cart.items.reduce((acc, item) => acc + item.quantity, 0);
      setCartCount(count);
    } catch (e) {
      console.error(e);
    }
  };

  useEffect(() => {
    fetchCartCount();
    
    // Check auth on load
    const checkAuth = async () => {
      const token = localStorage.getItem('token');
      if (token) {
        try {
          const data = await api.me();
          setAuthUser(data.username);
        } catch (e) {
          localStorage.removeItem('token');
        }
      }
    };
    checkAuth();
  }, []);

  return (
    <BrowserRouter>
      <div className="app-container">
        <Header cartCount={cartCount} authUser={authUser} setAuthUser={setAuthUser} />
        <main className="main-content">
          <Routes>
            <Route path="/" element={<ProductList />} />
            <Route path="/product/:id" element={<ProductDetail updateCartCount={fetchCartCount} authUser={authUser} />} />
            <Route path="/cart" element={<Cart updateCartCount={fetchCartCount} />} />
            <Route path="/checkout" element={<Checkout updateCartCount={fetchCartCount} />} />
            <Route path="/login" element={<Login setAuthUser={setAuthUser} />} />
            <Route path="/register" element={<Register />} />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  );
}

export default App;
