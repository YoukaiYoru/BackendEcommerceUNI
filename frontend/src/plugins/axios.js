import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'https://localhost:8080/',
  // Aquí puedes añadir más configuraciones como headers por defecto
});

export default axiosInstance;