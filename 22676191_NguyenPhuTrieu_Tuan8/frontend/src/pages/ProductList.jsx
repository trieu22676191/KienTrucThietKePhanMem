import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { api } from '../api';
import { PackageSearch } from 'lucide-react';

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const data = await api.getProducts();
        setProducts(data);
      } catch (error) {
        console.error('Failed to fetch products', error);
      } finally {
        setLoading(false);
      }
    };
    fetchProducts();
  }, []);

  if (loading) {
    return (
      <div className="loading">
        <div className="spinner"></div>
      </div>
    );
  }

  if (products.length === 0) {
    return (
      <div className="empty-state">
        <PackageSearch className="empty-icon" />
        <h2>No Products Found</h2>
        <p style={{ color: 'var(--text-muted)' }}>Check back later for exciting deals!</p>
      </div>
    );
  }

  return (
    <div>
      <h1 className="page-title">⚡ Flash Deals ⚡</h1>
      <div className="product-grid">
        {products.map(product => (
          <Link to={`/product/${product.id}`} key={product.id} className="product-card">
            {/* <img src={product.image} alt={product.name} className="product-image" loading="lazy" /> */}
            <div className="product-info">
              <h3 className="product-title">{product.name}</h3>
              <div className="product-price">${product.price.toLocaleString()}</div>
              <div className="product-stock">
                <div className={`stock-indicator ${product.stock <= 5 && product.stock > 0 ? 'low' : ''}`} 
                     style={{ backgroundColor: product.stock === 0 ? 'var(--text-muted)' : '' }}></div>
                {product.stock > 0 ? `${product.stock} in stock` : 'Out of stock'}
              </div>
              <button className="btn btn-primary" disabled={product.stock === 0}>
                {product.stock > 0 ? 'View Deal' : 'Sold Out'}
              </button>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default ProductList;
