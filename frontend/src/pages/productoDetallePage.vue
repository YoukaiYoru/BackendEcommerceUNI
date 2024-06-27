<template>
   <div v-if="productoSeleccionado" class="product-detail">
      <h1>{{ productoSeleccionado.product_name }}</h1>
      <p>{{ productoSeleccionado.product_description }}</p>
      <p>Price: s/. {{ productoSeleccionado.product_price }}</p>
      <button @click="addToCart">Add to Cart</button>
   </div>
   <div v-else-if="loading">
   <p>Cargando producto...</p>
   </div>
   <div v-else>
   <p>{{ error }}</p>
   </div>
</template>

<script setup>

import { computed } from 'vue';
import { useProductosStore } from '@/stores/productosStore';
import { useRoute } from 'vue-router';

const store = useProductosStore();
const route = useRoute();
const productoSeleccionado = computed(()=> {
   return store.getProductos.find((producto) => producto.id_product === Number(route.params.id));
})

</script>

<style scoped>
.product-detail {
   /* Add your styles here */
}
</style>