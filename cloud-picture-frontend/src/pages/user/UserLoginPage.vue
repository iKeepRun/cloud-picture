<template>
    <div class="userLoginPage">
        <h1 class="title">智能云图库-用户登录</h1>
        <div class="desc">企业级智能云图库</div>
        <a-form :model="formState" name="basic" autocomplete="off" @finish="handleSubmit">
            <a-form-item name="userAccount" :rules="[{ required: true, message: '请输入账号' }]">
                <a-input v-model:value="formState.userAccount" placeholder="请输入账号" />
            </a-form-item>

            <a-form-item name="userPassword"
                :rules="[{ required: true, message: '请输入密码' }, { min: 8, message: '密码不能少于8位' }]">
                <a-input-password v-model:value="formState.userPassword" placeholder="请输入密码" />
            </a-form-item>

            <div class="tips">
                没有账号？
                <RouterLink to="/user/register">去注册</RouterLink>
            </div>

            <a-form-item>
                <a-button type="primary" html-type="submit" style="width: 100%;">登录</a-button>
            </a-form-item>
        </a-form>
    </div>
</template>
<script lang="ts" setup>
import { useLoginUserStore } from '@/stores/useLoginUserStore';
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import {loginUsingPost} from '../../api/userController';
import { message } from 'ant-design-vue';
const router=useRouter()
const loginUserStore=useLoginUserStore()

const formState = reactive<API.UserLoginRequest>({
    userAccount: '',
    userPassword: '',
});
const handleSubmit = async (values:any) => {
    const res =await loginUsingPost(values)
    if(res.data.code===0&& res.data.data){
        await loginUserStore.fetchLoginUser()
        message.success('登录成功')
        router.push({
            path:"/",
            replace:true
        })
    }else {
        message.error('登录失败，'+res.data.msg)
    }
}
</script>

<style>
  .userLoginPage {
    max-width: 360px;
    margin: 0 auto;
    padding-top: 120px;
  }

  .title {
    text-align: center;
    /* margin-bottom: 16px; */
  }
  .desc {
    text-align: center;
    margin-bottom: 16px;
    color: #BBB;
  }
  .tips {
    text-align: right;
    margin-bottom: 16px;
    font-size: 14px;
  }
</style> 