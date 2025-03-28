self.addEventListener('push', function(event) {
  console.log(event);
  let jsonData;
  try {
    jsonData = event.data ? event.data.json() : { data: { message: '기본 메시지' } };
  } catch (error) {
    console.error('Error parsing push data:', error);
    jsonData = { data: { message: '기본 메시지' } }; // 기본 메시지
  }

  const options = {
    body: jsonData.data.message,
    icon: 'favicon.ico',
    badge: 'favicon.ico',
    // 추가 옵션
    requireInteraction: true, // 알림이 자동으로 사라지지 않음
    vibrate: [200, 100, 200] // 진동 패턴
  };


  console.log('Notification options:', options);

  event.waitUntil(
      self.registration.showNotification('커뮤니티 피드 서비스 알림', options)
          .then(() => console.log('Notification displayed successfully'))
          .catch(err => console.error('Error displaying notification:', err))
  );
});