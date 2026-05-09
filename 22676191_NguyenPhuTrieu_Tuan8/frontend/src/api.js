import axios from 'axios';

// Define base URLs for Processing Units (PUs)
// Assuming they run locally on different ports for the microservices architecture
const PU1_URL = 'http://192.168.137.16:8081'; // Product PU
const PU2_URL = 'http://192.168.137.92:8082'; // Cart PU
const PU3_URL = 'http://192.168.137.92:8083'; // Order PU
const AUTH_URL = 'http://localhost:8080'; // Auth PU

// Setup axios interceptor to add token
axios.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Mock data to ensure the UI looks amazing even if backend is offline
const MOCK_PRODUCTS = [
  {
    id: 'p1',
    name: 'MacBook Pro M3 Max',
    price: 3499,
    description: 'The ultimate pro laptop with the incredibly fast M3 Max chip. Perfect for high-end developers and creators.',
    image: 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8?auto=format&fit=crop&q=80&w=800',
    stock: 5,
  },
  {
    id: 'p2',
    name: 'Sony WH-1000XM5',
    price: 399,
    description: 'Industry leading noise canceling headphones. Experience next-level audio quality and supreme comfort.',
    image: 'https://images.unsplash.com/photo-1618366712010-f4ae9c647dcb?auto=format&fit=crop&q=80&w=800',
    stock: 12,
  },
  {
    id: 'p3',
    name: 'PlayStation 5 Pro',
    price: 499,
    description: 'Experience lightning-fast loading with an ultra-high speed SSD, deeper immersion with support for haptic feedback.',
    image: 'https://images.unsplash.com/photo-1606813907291-d86efa9b94db?auto=format&fit=crop&q=80&w=800',
    stock: 2,
  },
  {
    id: 'p4',
    name: 'iPhone 15 Pro Max',
    price: 1199,
    description: 'Forged in titanium and featuring the groundbreaking A17 Pro chip, a customizable Action button, and a more versatile Pro camera system.',
    image: 'https://images.unsplash.com/photo-1695048133142-1a20484d2569?auto=format&fit=crop&q=80&w=800',
    stock: 0,
  }
];

let MOCK_CART = { items: [], total: 0 };

export const api = {
  // PU1: Product PU
  getProducts: async () => {
    try {
      const response = await axios.get(`${PU1_URL}/products`);
      return response.data;
    } catch (error) {
      console.warn("Product PU offline, using mock data");
      return new Promise(resolve => setTimeout(() => resolve(MOCK_PRODUCTS), 600));
    }
  },

  getProductById: async (id) => {
    try {
      const response = await axios.get(`${PU1_URL}/products/${id}`);
      return response.data;
    } catch (error) {
      console.warn("Product PU offline, using mock data");
      const product = MOCK_PRODUCTS.find(p => p.id === id);
      return new Promise((resolve, reject) =>
        setTimeout(() => product ? resolve(product) : reject(new Error("Not found")), 400)
      );
    }
  },

  // PU2: Cart PU
  getCart: async () => {
    try {
      const response = await axios.get(`${PU2_URL}/cart`);
      return response.data;
    } catch (error) {
      if (error.response) {
        throw error;
      }
      console.warn("Cart PU offline, using mock data");
      return new Promise(resolve => setTimeout(() => resolve(MOCK_CART), 300));
    }
  },

  addToCart: async (productId, quantity = 1) => {
    try {
      const response = await axios.post(`${PU2_URL}/cart/add`, { productId, quantity });
      return response.data;
    } catch (error) {
      if (error.response) {
        throw error;
      }
      console.warn("Cart PU offline, using mock data");
      return new Promise((resolve) => {
        setTimeout(() => {
          const product = MOCK_PRODUCTS.find(p => p.id === productId);
          if (product) {
            const existing = MOCK_CART.items.find(i => i.productId === productId);
            if (existing) {
              existing.quantity += quantity;
            } else {
              MOCK_CART.items.push({
                productId,
                quantity,
                name: product.name,
                price: product.price,
                image: product.image
              });
            }
            MOCK_CART.total = MOCK_CART.items.reduce((acc, item) => acc + (item.price * item.quantity), 0);
          }
          resolve(MOCK_CART);
        }, 400);
      });
    }
  },

  clearCartMock: () => {
    MOCK_CART = { items: [], total: 0 };
  },

  // PU3: Order PU
  checkout: async (checkoutData) => {
    try {
      const response = await axios.post(`${PU3_URL}/order/checkout`, checkoutData);
      return response.data;
    } catch (error) {
      if (error.response) {
        throw error;
      }
      console.warn("Order PU offline, using mock data");
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          if (MOCK_CART.items.length === 0) {
            reject(new Error("Cart is empty"));
          } else {
            api.clearCartMock();
            resolve({ orderId: `ORD-${Math.floor(Math.random() * 10000)}`, status: 'SUCCESS' });
          }
        }, 1000);
      });
    }
  },

  // Auth PU
  login: async (username, password) => {
    const response = await axios.post(`${AUTH_URL}/api/auth/login`, { username, password });
    return response.data;
  },
  
  register: async (username, password) => {
    const response = await axios.post(`${AUTH_URL}/api/auth/register`, { username, password });
    return response.data;
  },

  me: async () => {
    const response = await axios.get(`${AUTH_URL}/api/auth/me`);
    return response.data;
  }
};
