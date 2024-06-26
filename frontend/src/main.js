/**
 * main.js
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Plugins
import { registerPlugins } from '@/plugins'

// Components
import App from './App.vue'

// Composables
import { createApp } from 'vue'
import { createPinia	 } from 'pinia'
import router from './router'
import axiosInstance from './plugins/axios';


const app = createApp(App)
const pinia = createPinia()
registerPlugins(app)

app.config.globalProperties.$axios = axiosInstance;
app.use(router).use(pinia).mount('#app')
