

class Product{
    constructor(productId){
        this.productId = productId;
    }
    registerEvent = () =>{
        $("#amount_manager").addEventListener('click', (event) =>{
        if(event.target.tagName === 'A') clickCallback(event.target)
        });
    }
    clickCallback = (elem) =>{
        if(elem.classList.contains('up')){
            increaseAmount();
            return;
        }
        decreaseAmount();

    }

    increaseAmount = (amount) =>{

        fetchManager({
              url: '/api/cart/1',
              method: 'post',
        		body: {count:amount},
              headers: { 'content-type': 'application/json'},
              callback: (data) => {console.log(data)}
            });
    }


}
