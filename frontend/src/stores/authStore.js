import axios from 'axios';
import { defineStore } from 'pinia';


const api = 'http://localhost:8080/user/acceder'

export const useAuthStore = defineStore('auth', {
   state: () => ({
      user: null,
      isAuthenticated: false,
      error: null,
   }),
   actions: {
      async login(credentials) {
         try {
            const response = await axios.post(api, credentials);
            this.user=response.data;
            this.isAuthenticated = true;
            localStorage.setItem('user', JSON.stringify(response.data));
         }
         catch (error) {
            console.error('Error al iniciar sesión:', error);
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