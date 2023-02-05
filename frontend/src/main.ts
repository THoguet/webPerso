import { createApp } from 'vue'
import { createRouter, createWebHashHistory } from 'vue-router'
import './style.css'
import App from './App.vue'
import Accueil from './components/Accueil.vue'

export const routes = [
	{ path: '/', component: Accueil, title: "Accueil" },
	// { path: '/about', component: About },
]

const router = createRouter({
	// 4. Provide the history implementation to use. We are using the hash history for simplicity here.
	history: createWebHashHistory(),
	routes, // short for `routes: routes`
})

createApp(App).use(router).mount('#app')
