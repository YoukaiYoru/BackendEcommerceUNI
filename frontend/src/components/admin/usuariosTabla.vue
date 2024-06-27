<template>
   <v-data-table
   :headers="header"
   :items="filteredUsers"
   item-key="idClient">
   <template v-slot:top>
   
   <v-card-title class="d-flex align-center pe-2">
      <v-icon icon="mdi-account-multiple-outline"></v-icon> &nbsp;
      Clientes
      <v-spacer></v-spacer>

      <v-text-field
      v-model="search"
      density="compact"
      label="Search"
      prepend-inner-icon="mdi-magnify"
      variant="solo-filled"
      flat
      hide-details
      single-line
      ></v-text-field>
      
   </v-card-title>

   <v-divider></v-divider>    
   </template>
   </v-data-table>
</template>

<script lang="js" setup>
import { useClientesStore } from '@/stores/clientesStore.js';
import { computed, onMounted,ref } from 'vue';
onMounted(()=>{
   clientesStore.fetchClientes();
})

const clientesStore = useClientesStore(); // Define the clientesStore variable before using it

const usuarios = computed(()=> clientesStore.getClientes)
const search = ref('');
const filteredUsers = computed(() => {
   return usuarios.value.filter(item => 
      item.clientUser && item.clientUser.toLowerCase().includes(search.value.toLowerCase())
   );
});
</script>