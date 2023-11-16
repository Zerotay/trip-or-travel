import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { logoutUser } from '@/api/user.js'
import { useRouter } from 'vue-router'

export const loginStore = defineStore(
  'loginUser',
  () => {
    //state
    const userId = ref('')
    const userProfile = ref('')
    const userNickname = ref('')
    const router = useRouter()

    function Funclogout() {
      logoutUser(
        (data) => {
          console.log('logout success', data)
          userId.value = ''
          userProfile.value = ''
          userNickname.value = ''
          router.push({ name: 'main' })
        },
        (error) => {
          console.log('logout fail', error)
        }
      )
    }
    return { userId, userProfile, userNickname, Funclogout }
  },
  {
    persist: {
      beforeRestore: (ctx) => console.log(`store 복구 시작: ${ctx.store.$id}`),
      afterRestore: (ctx) => console.log(`store 복구 완료: ${ctx.store.$id}`)
    }
  }
)
