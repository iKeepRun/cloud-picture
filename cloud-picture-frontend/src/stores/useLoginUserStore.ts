import { defineStore } from "pinia";
import { ref } from "vue";
import { getLoginUserUsingGet} from "../api/userController"

export const useLoginUserStore=defineStore("loginUser",() => {
    const loginUser=ref<API.LoginUserVO>({
        userName:"未登录"
    })


    async function fetchLoginUser() {
       const result= await getLoginUserUsingGet()
        if(result.data.code===0&&result.data.data){
          loginUser.value=result.data.data
        }
      }
      


    function setLoginUser(newLoginUser:any){
         loginUser.value=newLoginUser
    }


    return { loginUser,fetchLoginUser,setLoginUser}
})



