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
    const hasUser = await this.github.getUser(nickname);
    if (hasUser) {
      this.userProfileView.show(this.github.profile);
      this.userReposView.show(this.github.repos);
      return ;
    }

    this.userProfileView.show();
    this.userReposView.hide();
  }

}
