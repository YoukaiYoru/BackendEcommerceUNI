<template>
  <v-app>
    <v-toolbar>
      <NavBar />
    </v-toolbar>
    <v-main class="pa-0">
      <router-view />
    </v-main>
  </v-app>
</template>

<script setup>
  import NavBar from '@/components/NavBar.vue'
</script>
