declare namespace API {
  type CommonResultBoolean_ = {
    code?: number
    data?: boolean
    msg?: string
  }

  type CommonResultLoginUserVO_ = {
    code?: number
    data?: LoginUserVO
    msg?: string
  }

  type CommonResultLong_ = {
    code?: number
    data?: number
    msg?: string
  }

  type CommonResultPageUserVO_ = {
    code?: number
    data?: PageUserVO_
    msg?: string
  }

  type CommonResultUser_ = {
    code?: number
    data?: User
    msg?: string
  }

  type CommonResultUserVO_ = {
    code?: number
    data?: UserVO
    msg?: string
  }

  type DeleteRequest = {
    id?: number
  }

  type getUserByIdUsingGETParams = {
    /** id */
    id?: number
  }

  type getUserVOByIdUsingGETParams = {
    /** id */
    id?: number
  }

  type LoginUserVO = {
    createTime?: string
    editTime?: string
    id?: number
    updateTime?: string
    userAccount?: string
    userAvatar?: string
    userName?: string
    userProfile?: string
    userRole?: string
  }

  type PageUserVO_ = {
    current?: number
    pages?: number
    records?: UserVO[]
    size?: number
    total?: number
  }

  type User = {
    createTime?: string
    editTime?: string
    id?: number
    isDelete?: number
    updateTime?: string
    userAccount?: string
    userAvatar?: string
    userName?: string
    userPassword?: string
    userProfile?: string
    userRole?: string
  }

  type UserAddRequest = {
    userAccount?: string
    userAvatar?: string
    userName?: string
    userPassword?: string
    userProfile?: string
    userRole?: string
  }

  type UserLoginRequest = {
    userAccount?: string
    userPassword?: string
  }

  type UserQueryRequest = {
    current?: number
    id?: number
    orderBy?: string
    orderType?: string
    pageSize?: number
    userAccount?: string
    userName?: string
    userProfile?: string
    userRole?: string
  }

  type UserRegisterRequest = {
    checkPassword?: string
    password?: string
    userAccount?: string
  }

  type UserUpdateRequest = {
    id?: number
    userAccount?: string
    userAvatar?: string
    userName?: string
    userProfile?: string
    userRole?: string
  }

  type UserVO = {
    createTime?: string
    id?: number
    userAccount?: string
    userAvatar?: string
    userName?: string
    userProfile?: string
    userRole?: string
  }
}
