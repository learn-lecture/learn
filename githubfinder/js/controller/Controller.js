export default class Controller {

  constructor(github, { searchUserView, userProfileView }) {
    this.github = github;
    this.searchUserView = searchUserView;
    this.UserProfileView = userProfileView;

    this.subscribeViewEvents();
  }

  subscribeViewEvents() {
    this.searchUserView
        .on('search', (event) => this.search(event.detail));
  }

  async search({ nickname }) {
    const response = await this.github.getUser(nickname);
    if (response) {
      this.UserProfileView.show(this.github);
    } else {
      this.UserProfileView.show();
    }
  }

}
