<template>
  <v-app>
    <NavBar />
    <v-main>
      <router-view />
    </v-main>
    <AppFooter />
  </v-app>
</template>

<script setup>
  import NavBar from '@/components/NavBar.vue';
  import AppFooter from '@/components/AppFooter.vue';

</script>
