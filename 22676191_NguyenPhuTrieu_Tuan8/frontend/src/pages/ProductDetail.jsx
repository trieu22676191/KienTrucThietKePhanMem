import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { api } from '../api';
import { ShoppingCart, ArrowLeft, CheckCircle2, Lock } from 'lucide-react';

const ProductDetail = ({ updateCartCount, authUser }) => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [adding, setAdding] = useState(false);
  const [toast, setToast] = useState(null);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const data = await api.getProductById(id);
        setProduct(data);
      } catch (error) {
        console.error('Failed to fetch product', error);
      } finally {
        setLoading(false);
      }
    };
    fetchProduct();
  }, [id]);

  const handleAddToCart = async () => {
    if (!authUser) {
      setToast('You must login to add to cart');
      setTimeout(() => navigate('/login'), 1500);
      return;
    }

    setAdding(true);
    try {
      await api.addToCart(product.id, 1);
      updateCartCount();
      setToast('Added to cart successfully!');
      setTimeout(() => setToast(null), 3000);
    } catch (error) {
      console.error('Failed to add to cart', error);
      setToast(error.response?.data?.message || error.response?.data || 'Failed to add to cart (Server Error)');
    } finally {
      setAdding(false);
    }
  };

  if (loading) return <div className="loading"><div className="spinner"></div></div>;
  if (!product) return <div className="empty-state"><h2>Product not found</h2></div>;

  return (
    <div>
      <button onClick={() => navigate(-1)} className="btn" style={{ background: 'transparent', padding: 0, marginBottom: '2rem', width: 'auto' }}>
        <ArrowLeft size={20} /> Back to Products
      </button>
      
      <div className="product-detail">
        {/* <img src={product.image} alt={product.name} className="detail-image" /> */}
        <div className="detail-info">
          <h1 className="detail-title">{product.name}</h1>
          <div className="product-price" style={{ fontSize: '2.5rem' }}>${product.price.toLocaleString()}</div>
          
          <div className="product-stock" style={{ fontSize: '1rem', marginBottom: '2rem' }}>
            <div className={`stock-indicator ${product.stock <= 5 && product.stock > 0 ? 'low' : ''}`}
                 style={{ backgroundColor: product.stock === 0 ? 'var(--text-muted)' : '' }}></div>
            {product.stock > 0 ? `${product.stock} items left in stock` : 'Out of stock'}
          </div>
          
          <p className="detail-desc">{product.description}</p>
          
          <div style={{ marginTop: 'auto' }}>
            <button 
              className="btn btn-primary" 
              onClick={handleAddToCart}
              disabled={product.stock === 0 || adding}
              style={{ padding: '1rem', fontSize: '1.125rem' }}
            >
              {!authUser ? (
                 <><Lock size={20} /> Login to Buy</>
              ) : adding ? (
                <><div className="spinner" style={{ width: '20px', height: '20px', borderWidth: '2px' }}></div> Adding...</>
              ) : (
                <><ShoppingCart /> {product.stock > 0 ? 'Add to Cart' : 'Sold Out'}</>
              )}
            </button>
          </div>
        </div>
      </div>

      {toast && (
        <div className="toast" style={{ borderLeftColor: authUser ? 'var(--success)' : 'var(--accent)' }}>
          <CheckCircle2 color={authUser ? "var(--success)" : "var(--accent)"} />
          {toast}
        </div>
      )}
    </div>
  );
};

export default ProductDetail;
