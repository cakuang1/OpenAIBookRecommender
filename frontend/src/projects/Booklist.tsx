import React from "react";




// BOOK LIST Used for both the User Book List and Reccomendation Book List



interface Book {
    title: string;
    author: string;
    isbn: string;
    pictureurl: string;
  }

interface BookItemProps {
  book: Book;
  onClick: () => void;
}

  const BookItem: React.FC<BookItemProps> = ({ book, onClick }) => {
    const { title, author, isbn, pictureurl } = book;
  
    const handleClick = () => {
      // Call the onClick handler with the ISBN when the BookItem is clicked
      onClick();
    };
  
    return (
      <div
        className="flex items-center border p-2 bg-black text-yellow-500 shadow-md border-yellow-500 hover:bg-red-500 transition duration-300 ease-in-out"
        onClick={handleClick} // Add the click event handler
      >
        <img src={pictureurl} className="w-16 h-24 mr-2" />
        <div>
          <h2 className="text-lg font-semibold mb-1">{title}</h2>
          <p className="text-gray-400">ISBN: {isbn}</p>
          <p className="text-gray-400">Published: {author}</p>
        </div>
      </div>
    );
  };
  
  interface ListofBooks {
    bookitems: Book[];
    onBookClick: () => void; 
  }
  
  const BookList: React.FC<ListofBooks> = ({ bookitems,onBookClick}) => {
    return (
      <div>
        {bookitems.map((book, index) => (
          <BookItem
            key={index} 
            book={book}
            onClick={onBookClick}
          />
        ))}
      </div>
    );
  };


export default BookList