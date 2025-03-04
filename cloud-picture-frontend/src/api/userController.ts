// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** addUser POST /api/user/addUser */
export async function addUserUsingPost(body: API.UserAddRequest, options?: { [key: string]: any }) {
  return request<API.CommonResultLong_>('/api/user/addUser', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}

/** deleteUserById DELETE /api/user/delete */
export async function deleteUserByIdUsingDelete(
  body: API.DeleteRequest,
  options?: { [key: string]: any }
) {
  return request<API.CommonResultBoolean_>('/api/user/delete', {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}

/** getUserById GET /api/user/get */
export async function getUserByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserByIdUsingGETParams,
  options?: { [key: string]: any }
) {
  return request<API.CommonResultUser_>('/api/user/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  })
}

/** getUserVOById GET /api/user/get/vo */
export async function getUserVoByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserVOByIdUsingGETParams,
  options?: { [key: string]: any }
) {
  return request<API.CommonResultUserVO_>('/api/user/get/vo', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  })
}

/** getLoginUser GET /api/user/getLoginUser */
export async function getLoginUserUsingGet(options?: { [key: string]: any }) {
  return request<API.CommonResultLoginUserVO_>('/api/user/getLoginUser', {
    method: 'GET',
    ...(options || {}),
  })
}

/** listUserVOByPage POST /api/user/list/page/vo */
export async function listUserVoByPageUsingPost(
  body: API.UserQueryRequest,
  options?: { [key: string]: any }
) {
  return request<API.CommonResultPageUserVO_>('/api/user/list/page/vo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}

/** login POST /api/user/login */
export async function loginUsingPost(body: API.UserLoginRequest, options?: { [key: string]: any }) {
  return request<API.CommonResultLoginUserVO_>('/api/user/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}

/** logout GET /api/user/logout */
export async function logoutUsingGet(options?: { [key: string]: any }) {
  return request<API.CommonResultBoolean_>('/api/user/logout', {
    method: 'GET',
    ...(options || {}),
  })
}

/** registerUser POST /api/user/register */
export async function registerUserUsingPost(
  body: API.UserRegisterRequest,
  options?: { [key: string]: any }
) {
  return request<API.CommonResultLong_>('/api/user/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}

/** updateUser POST /api/user/update */
export async function updateUserUsingPost(
  body: API.UserUpdateRequest,
  options?: { [key: string]: any }
) {
  return request<API.CommonResultBoolean_>('/api/user/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}
