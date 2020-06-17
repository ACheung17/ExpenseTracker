import React, { Component } from 'react';
import AppNav from "./AppNav";
import Footer from "./Footer"
import { Container } from 'reactstrap';

class Category extends Component {
    state = {
        isLoading: true,
        Categories: []
    }

    async componentDidMount(){
        const response=await fetch('/api/categories');
        const body = await response.json();        
        this.setState({Categories : body , isLoading: false});
    }

    render() { 
        const {Categories , isLoading} = this.state;
        if(isLoading) 
            return (<div>Loading...</div>);
        return ( 
                <div>
                    <AppNav />
                    <Container>
                        <h2>Categories</h2>
                        {Categories.map( category => 
                            <p id={category.id}>
                                {category.name}
                            </p>
                        )}
                    </Container>
                    <div className="footer">
                    <Footer />
                </div> 
                </div>
         );
    }
}

export default Category;


//import React, {useState}  from 'react';
// function Category() {
//     const [category, setState] = useState([]);

//         fetch('/api/categories')
//         .then(res => res.json())
//         .then(data => this.setState({ category: data, isLoading: false }));
    
// }
