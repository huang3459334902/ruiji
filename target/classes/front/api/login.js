function loginApi(params) {
    return $axios({
      'url': '/user/login',
      'method': 'post',
      data: {...params}
    })
  }

function loginoutApi() {
  return $axios({
    'url': '/user/loginout',
    'method': 'post',

  })
}

function sendMsgApi(data) {
    return $axios({
        'url':'/user/sendMsg',
        'method':'post',
        data
    })
}

  