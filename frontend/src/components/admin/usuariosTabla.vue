<template>
   <v-data-table
   :items="usuarios">
      
   </v-data-table>
</template>

<script lang="js" setup>
import { useClientesStore } from '@/stores/clientesStore.js';
import { computed, onMounted } from 'vue';
const clientesStore = useClientesStore();
const usuarios = computed(()=> clientesStore.clientes)

onMounted(()=>{
   clientesStore.fetchClientes();
})
</script>