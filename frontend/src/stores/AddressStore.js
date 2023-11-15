import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { getSido, getGugun } from '@/api/region.js'

export const AddressStore = defineStore('AddressCode', () => {
  //state
  const sidos = ref([])
  const gugunBySido = ref({})
  let guguns = []
  getSido(
    ({ data }) => {
      data.data.map(({ sidoCode, sidoName }) => {
        sidos.value.push({
          text: sidoName,
          value: sidoCode
        })
      })
      sidos.value.map(({ text, value }) => {
        getGugun(
          value,
          ({ data }) => {
            guguns = []
            data.data.map((gugun) => {
              guguns.push({
                text: gugun.gugunName,
                value: gugun.gugunCode
              })
            })
            gugunBySido.value[value] = guguns
          },
          (error) => {
            console.log(error)
          }
        )
      })
    },
    (error) => {
      console.log('error', error)
    }
  )

  // 시도 조회

  // const count = ref(0)
  // const doubleCount = computed(() => count.value * 2)

  // function increment() {
  //   count.value++
  // }

  return { sidos, gugunBySido }
})