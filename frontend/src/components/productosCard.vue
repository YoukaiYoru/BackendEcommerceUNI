<template>
   <v-card max-width="300"
   max-height="400">
      <v-img :src="imagenProducto" height="200"></v-img>
      <v-card-title>{{ nombreProducto }}</v-card-title>
      <v-card-subtitle>Precio: {{ precio }}</v-card-subtitle>
      <v-card-actions>
         <v-btn color="primary" @click="agregarCarrito">Agregar al carrito</v-btn>
      </v-card-actions>
   </v-card>
</template>


<style scoped>
/* Estilos personalizados para la v-card */
</style>
<script setup>

import { useProductosStore } from '@/stores/productosStore';

const store = useProductosStore();




const props = defineProps({
   nombreProducto: String,
   imagenProducto: String,
   precio: Number,
});


const agregarCarrito = () => {
   store.agregarProducto({
      nombre: props.nombreProducto,
      precio: props.precio,
      imagen: props.imagenProducto,
   });
};

</script>