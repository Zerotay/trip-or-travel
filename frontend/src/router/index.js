import { createRouter, createWebHistory } from 'vue-router'
import TheHomeView from '@/views/TheHomeView.vue'
import TheTestView from '@/views/TheTestView.vue'
import userRouter from './UserRouter'
import boardRouter from './BoardRouter'
import commentRouter from './CommentRouter'
import App from '@/App.vue'

const router = createRouter({
  history: createWebHistory(),
  // history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/', //처음 실행시 보여줄 main
      name: 'main',
      component: TheHomeView,
      children: []
    },
    {
      path: '/test',
      name: 'test',
      component: TheTestView
    },
    ...userRouter,
    ...boardRouter,
    ...commentRouter
  ]
})

export default router
