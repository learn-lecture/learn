export default class Controller {

  constructor(user, { searchUserView }) {
    this.user = user;
    this.searchUserView = searchUserView;

    this.subscribeViewEvents();
  }

  subscribeViewEvents() {
    this.searchUserView
        .on('search', (event) => this.search(event.detail));
  }

  search({ nickname }) {
    console.log(nickname);
  }

}
