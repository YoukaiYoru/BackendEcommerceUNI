import { defineStore } from 'pinia';
import axios from 'axios';

export const useAuthStore = defineStore('auth', {
   state: () => ({
      user: null,
      isAuthenticated: false,
      error: null,
   }),
   actions: {
      async login(credentials) {
         try {
         const response = await axios.post('http://localhost:8080/user/acceder', credentials);
         this.user = response.data.user; // Guarda el usuario en el estado
         this.isAuthenticated = true;
         // Puedes manejar el token JWT u otros detalles de sesión aquí
         } catch (error) {
         console.error('Error en el inicio de sesión:', error);
         this.error = error.message;
         }
      },
      async logout() {
         try {
         // Lógica para cerrar sesión, por ejemplo, invalidar el token JWT
         this.user = null;
         this.isAuthenticated = false;
         } catch (error) {
         console.error('Error al cerrar sesión:', error);
         this.error = error.message;
         }
      },
   },
});