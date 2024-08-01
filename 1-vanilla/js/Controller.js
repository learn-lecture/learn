const tag = "[Controller]";

export default class Controller {
  constructor(store, {searchFormView, searchResultView, tabView, keywordListView}) {
    this.store = store;
    
    this.searchFormView = searchFormView;
    this.searchResultView = searchResultView;
    this.tabView = tabView;
    this.keywordListView = keywordListView;

    this.subscribeViewEvents();
    this.render();
  }

  subscribeViewEvents() {
    this.searchFormView
    .on('@submit', (event) => this.search(event.detail.value))
    .on('@reset', () => this.reset());

    this.tabView
    .on('@tabchange', (event) => {this.select(event.detail.tab);});
  }

  select(tab) {
    this.store.select(tab);
    this.tabView.show(this.store.selectedTab);
  }

  search(keyword) {
    this.store.search(keyword);
    this.render();
  }

  reset() {
    this.store.reset();
    this.render();
  }

  render() {
    if (this.store.searchKeyword.length > 0) {
      return this.renderSearchResult();
    }

    this.tabView.show(this.store.selectedTab);
    this.keywordListView.show(this.store.getKeywordList());
    this.searchResultView.hide();
  }

  renderSearchResult() {
    this.tabView.hide();
    this.searchResultView.show(this.store.searchResult);
  }

}
