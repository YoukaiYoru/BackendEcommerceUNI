import axios from "axios";
import { defineStore } from "pinia";

export const useProductosStore = defineStore('productosStore',{
   state: () => ({
      listaProductos: [],
      productosCarrito: [],
      searchTerm: '',
   }),

   getters: {
      getProductos(state){
         return state.listaProductos;
      },
      getCarrito(state){
         return state.productosCarrito;
      }


   },

   actions: {
      async fetchProductos(){
         try {
            const response = await axios.get('http://localhost:8080/producto/get');
            this.listaProductos = response.data;
         } catch (error) {
            alert('Error al obtener los productos');
            console.error(error);
         }
      },

      agregarProducto(item){
         this.productosCarrito.push(item);
         console.log(item);
      },
      async deleteProducto(id) {
         try {
            await axios.delete(`http://localhost:8080/producto/delete/${id}`);
            // Remove the deleted product from the list
            this.listaProductos = this.listaProductos.filter(producto => producto.id !== id);
            console.log('Producto eliminado:', id);
         } catch (error) {
            console.error('Error al eliminar el producto:', error);
            alert('Error al eliminar el producto');
         }
      },
      async fetchProducto(id) {
         try {
            this.loading = true;
            const response = await axios.get(`http://localhost:8080/producto/get/id/${id}`);
            this.producto = response.data;
            } catch (error) {
            console.error('Error al obtener el producto:', error);
            this.error = error.message;
            } finally {
            this.loading = false;
            }
         },
   }

});
