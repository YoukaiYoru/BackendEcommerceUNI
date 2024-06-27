<template>
   <div class="product-detail">
      <h1>{{ productoSeleccionado.title }}</h1>
      <p>{{ productoSeleccionado.description }}</p>
      <p>Price: ${{ productoSeleccionado.price }}</p>
      <button @click="addToCart">Add to Cart</button>
   </div>
</template>

<script setup>

import { computed } from 'vue';
import { useProductosStore } from '@/stores/productosStore';
import { useRoute } from 'vue-router';

const store = useProductosStore();
const route = useRoute();
const productoSeleccionado = computed(()=> {
   return store.getProductos.find((producto) => producto.id === Number(route.params.id));
})

</script>

<style scoped>
.product-detail {
   /* Add your styles here */
}
</style>