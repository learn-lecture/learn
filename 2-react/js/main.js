class App extends React.Component {

    constructor() {
        super();

        this.state = {
            searchKeyword: "",
        };
    }

    render() {
        return (
            <>
                <header>
                    <h2 className="container">검색</h2>
                </header>
                <div className="container">
                    <form>
                        <input
                            type="text"
                            placeholder="검색어를 입력하세요."
                            value={this.state.searchKeyword}
                            autoFocus
                        />
                        <button type="reset" className="btn-reset"></button>
                    </form>
                </div>
            </>
        );
    }

}

ReactDOM.render(<App />, document.querySelector("#app"));