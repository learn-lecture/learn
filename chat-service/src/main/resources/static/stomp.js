const stompClient = new StompJs.Client({
  brokerURL: 'ws://localhost:8080/stomp/chats'
});

function toggleNewMessageIcon(chatroomId, toggle) {
  let currentChatroomId = $("#chatroom-id").val();
  if (toggle && (currentChatroomId != chatroomId)) {
    $("#new_" + chatroomId).show();
  } else {
    $("#new_" + chatroomId).hide();
  }
}

function updateMemberCount(chatroom) {
  $("#memberCount_" + chatroom.id).html(chatroom.memberCount);
}

stompClient.onConnect = (frame) => {
  setConnected(true);
  stompClient.subscribe('/sub/chats/updates', (chatMessage) => {
    toggleNewMessageIcon(JSON.parse(chatMessage.body).id, true);
    updateMemberCount(JSON.parse(chatMessage.body));
  })
};

stompClient.onWebSocketError = (error) => {
  console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
  console.error('Broker reported error: ' + frame.headers['message']);
  console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  $("#create").prop("disabled", !connected);
}

function connect() {
  stompClient.activate();
  showChatrooms();
}

function disconnect() {
  stompClient.deactivate();
  setConnected(false);
  exitChatRoom();
  $("#chatroom-list").html("");
  console.log("Disconnected");
}

function sendMessage() {
  let chatroomId = $("#chatroom-id").val();

  stompClient.publish({
    destination: "/pub/chats/" + chatroomId,
    body: JSON.stringify(
        {'message': $("#message").val()})
  });
  $("#message").val("")
}

function showMessage(chatMessage) {
  $("#messages").append(
      "<tr><td>" + chatMessage.sender + " : " + chatMessage.message
      + "</td></tr>");
}

function getRequestParam(currentChatroomId) {
  if (currentChatroomId === "") {
    return "";
  }

  return "?currentChatroomId=" + currentChatroomId;
}

function joinChatroom(chatroomId) {
  let currentChatroomId = $("#chatroom-id").val();

  $.ajax({
    type: 'POST',
    dataType: 'json',
    url: '/chats/' + chatroomId + getRequestParam(currentChatroomId),
    success: function (data) {
      console.log('show chatroom data: ', data);
      enterChatrooms(chatroomId, data);
    },
    error: function (request, status, error) {
      console.log('request: ', request);
      console.log('error: ', error);
    }
  })
}

function getDisplayValue(hasNewMessage) {
  return hasNewMessage ? "inline" : "none";
}

function renderChatrooms(chatrooms) {
  $("#chatroom-list").html("");
  for (let i = 0; i < chatrooms.length; i++) {
    $("#chatroom-list").append(
        "<tr onclick='joinChatroom(" + chatrooms[i].id + ")'>" +
        "<td>" + chatrooms[i].id + "</td>" +
        "<td>" + chatrooms[i].title +
        "<img src='new.png' id='new_" + chatrooms[i].id + "' style='display: " +
        getDisplayValue(chatrooms[i].hasNewMessage) + "'/> </td>" +
        "<td id='memberCount_" + chatrooms[i].id + "'>" + chatrooms[i].memberCount + "</td>" +
        "<td>" + chatrooms[i].createdAt + "</td>" +
        "</tr>"
    )
  }
}

function showChatrooms() {
  $.ajax({
    type: 'GET',
    dataType: 'json',
    url: '/chats',
    success: function (data) {
      console.log('show chatroom data: ', data);
      renderChatrooms(data);
    },
    error: function (request, status, error) {
      console.log('request: ', request);
      console.log('error: ', error);
    }
  })
}

let subscription;

function showMessages(chatroomId) {
  $.ajax({
    type: 'GET',
    dataType: 'json',
    url: '/chats/' + chatroomId + '/messages',
    success: function (data) {
      console.log("message data: ", data);
      for (let i = 0; i < data.length; i++) {
        showMessage(data[i]);
      }
    },
    error: function (request, status, error) {
      console.log('request: ', request);
      console.log('error: ', error);
    }
  })
}

function enterChatrooms(chatroomId, newMember) {
  $("#chatroom-id").val(chatroomId);
  $("#messages").html("");
  showMessages(chatroomId);
  $("#conversation").show();
  $("#send").prop("disabled", false);
  $("#leave").prop("disabled", false);
  toggleNewMessageIcon(chatroomId, false);

  if (subscription !== undefined) {
    subscription.unsubscribe();
  }

  subscription = stompClient.subscribe('/sub/chats/' + chatroomId,
      (chatMessage) => showMessage(JSON.parse(chatMessage.body)));
  console.log(subscription);

  if (newMember) {
    stompClient.publish({
      destination: "/pub/chats/" + chatroomId,
      body: JSON.stringify(
          {'message': "님이 방에 들어왔습니다."})
    })
  }
}

function createChatroom() {
  $.ajax({
    type: 'POST',
    dataType: 'json',
    url: "/chats?title=" + $("#chatroom-title").val(),
    success: function (data) {
      console.log('create chatroom data: ', data);
      showChatrooms();
      enterChatrooms(data.id, true);
    },
    error: function (request, status, error) {
      console.log('request: ', request);
      console.log('error: ', error);
    }
  })
}

function exitChatRoom() {
  $("#chatroom-id").val("");
  $("#conversation").hide();
  $("#send").prop("disabled", true);
  $("#leave").prop("disabled", true);
}

function leaveChatroom() {
  let chatRoomId = $("#chatroom-id").val();

  $.ajax({
    type: 'DELETE',
    dataType: 'json',
    url: '/chats/' + chatRoomId,
    success: function (data) {
      console.log('show chatroom data: ', data);
      showChatrooms();
      exitChatRoom();
    },
    error: function (request, status, error) {
      console.log('request: ', request);
      console.log('error: ', error);
    }
  })
}

$(function () {
  $("form").on('submit', (e) => e.preventDefault());
  $("#connect").click(() => connect());
  $("#disconnect").click(() => disconnect());
  $("#create").click(() => createChatroom());
  $("#leave").click(() => leaveChatroom());
  $("#send").click(() => sendMessage());
});