import {qs} from "../global/helpers.js";

export default class Controller {

  constructor(github, { searchUserView, userProfileView, userReposView, userCommitsView }) {
    this.github = github;
    this.searchUserView = searchUserView;
    this.userProfileView = userProfileView;
    this.userReposView = userReposView;
    this.spinner = qs('.spinner');

    this.subscribeViewEvents();
  }

  subscribeViewEvents() {
    this.searchUserView
        .on('search', (event) => this.search(event.detail));
  }

  async search({ nickname }) {
    this.loading();
    const hasUser = await this.github.getUser(nickname);

    if (hasUser) {
      this.userProfileView.show(this.github.profile);
      this.userReposView.show(this.github.repos);
    } else {
      this.userProfileView.show();
    }

    this.spinner.style.display = "none";
  }

  loading() {
    this.userProfileView.hide();
    this.userReposView.hide();

    this.spinner.style.display = "block";
  }

}
