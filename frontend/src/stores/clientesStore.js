import axios from "axios";
import { defineStore } from "pinia";

export const useClientesStore = defineStore('clientesStore',{
   state: () => ({
      listaClientes: [],
      cliente: {},
      searchTerm: '',
   }),
   getters: {
      getClientes(state){
         return state.listaClientes;
      },
      getCliente(state){
         return state.cliente;
      }
   },

   actions:{
      async fetchClientes(){
         try {
            const response = await axios.get('http://localhost:8080/user');
            this.listaClientes = response.data;
         } catch (error) {
            alert('Error al obtener los clientes');
            console.error(error);
         }
      }

      
   }
})