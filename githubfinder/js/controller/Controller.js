export default class Controller {

  constructor(github, { searchUserView, userProfileView, userReposView }) {
    this.github = github;
    this.searchUserView = searchUserView;
    this.userProfileView = userProfileView;
    this.userReposView = userReposView;

    this.subscribeViewEvents();
  }

  subscribeViewEvents() {
    this.searchUserView
        .on('search', (event) => this.search(event.detail));
  }

  async search({ nickname }) {
    const response = await this.github.getUser(nickname);
    if (response) {
      this.userProfileView.show(this.github);
    } else {
      this.userProfileView.show();
    }

    const repoResponse = await this.github.getUserRepo(nickname);
    if (repoResponse) {
      this.userReposView.show(this.github.repos);
    } else {
      this.userReposView.show();
    }
  }

}
