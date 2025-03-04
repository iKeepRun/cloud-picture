<template>
    <div class="userRegisterPage">
        <h1 class="title">智能云图库-用户注册</h1>
        <div class="desc">企业级智能云图库</div>
        <a-form :model="formState" name="basic" autocomplete="off" @finish="handleSubmit">
            <a-form-item name="userAccount" :rules="[{ required: true, message: '请输入账号' }]">
                <a-input v-model:value="formState.userAccount" placeholder="请输入账号" />
            </a-form-item>

            <a-form-item name="password"
                :rules="[{ required: true, message: '请输入密码' }, { min: 8, message: '密码不能少于8位' }]">
                <a-input-password v-model:value="formState.password" placeholder="请输入密码" />
            </a-form-item>
            <a-form-item name="checkPassword"
                :rules="[{ required: true, message: '请输入确认密码' }, { min: 8, message: '确认密码不能少于8位' }]">
                <a-input-password v-model:value="formState.checkPassword" placeholder="请输入确认密码" />
            </a-form-item>
            <div class="tips">已有账号? <router-link to="/user/login">登录</router-link></div>
            <a-form-item>
                <a-button type="primary" html-type="submit" style="width: 100%;">注册</a-button>
            </a-form-item>
        </a-form>
    </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue';
import { registerUserUsingPost } from '../../api/userController';
import { message } from 'ant-design-vue';
import { useRouter } from 'vue-router';

const router = useRouter()

const formState = reactive<API.UserRegisterRequest>({
    userAccount: '',
    password: '',
    checkPassword: ''
});

const handleSubmit = async (values: any) => {
    if (formState.password !== formState.checkPassword) {
        message.error("两次密码输入不一致")
        return
    }
    const res = await registerUserUsingPost(values)
    if (res.data.code === 0 && res.data.data) {
        message.success('注册成功')
        router.push({
            path: "/user/login",
            replace: true
        })
    } else {
        message.error('登录失败，' + res.data.msg)
    }
}

</script>

<style scoped>
.userRegisterPage {
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