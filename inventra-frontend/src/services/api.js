import axios from 'axios';

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8081/api',
  headers: { 'Content-Type': 'application/json' },
  timeout: 15000,
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('inventra_token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

api.interceptors.response.use(
  (response) => response,
  (error) => {
    const status = error.response?.status;
    const url = error.config?.url || '';
    const isAuthEndpoint = url.includes('/auth/login') || url.includes('/auth/register');
    if ((status === 401 || status === 403) && !isAuthEndpoint) {
      localStorage.removeItem('inventra_token');
      localStorage.removeItem('inventra_user');
      if (window.location.pathname !== '/login' && window.location.pathname !== '/register') {
        window.location.replace('/login');
      }
    }
    return Promise.reject(error);
  }
);

export const errMsg = (error) => {
  const data = error?.response?.data;
  if (typeof data === 'string' && data.trim()) return data;
  return data?.message || data?.error || data?.details || 'Something went wrong. Please try again.';
};

export default api;
