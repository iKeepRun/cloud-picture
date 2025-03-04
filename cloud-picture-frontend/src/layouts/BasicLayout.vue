<template>
    <div id="basiclayout">
        <a-layout style="min-height: 100vh;">
            <a-layout-header class="header">
                <GlobalHeader />
            </a-layout-header>
            <a-layout-content class="content">
                <RouterView />
            </a-layout-content>
            <a-layout-footer class="footer">
                <a href="www.github.com" target="_blank">智能云图库 协杭科技</a>
            </a-layout-footer>
        </a-layout>
    </div>

</template>

<script setup lang="ts">
import GlobalHeader from '@/components/GlobalHeader.vue';

</script>

<style scoped>
#basiclayout .header{
   padding: 0  10px;
   background: #fff;
    
}

#basiclayout .content {
    margin: 24px;
    padding: 24px;
    background: #fff;
    min-height: 280px;
    background: #efefef;
    margin-bottom: 28px;
}

#basiclayout .footer {  
    text-align: center;
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    
}

</style>