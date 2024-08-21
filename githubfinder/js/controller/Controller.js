export default class Controller {

  constructor(github, { searchUserView }) {
    this.github = github;
    this.searchUserView = searchUserView;

    this.subscribeViewEvents();
  }

  subscribeViewEvents() {
    this.searchUserView
        .on('search', (event) => this.search(event.detail));
  }

  async search({ nickname }) {
    const response = await this.github.getUser(nickname);
    if (response) {
      console.log(this.github);
    } else {
      console.log("nono");
    }
  }

}
