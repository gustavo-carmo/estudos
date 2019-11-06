import PouchDB from 'pouchdb'

const db = new PouchDB('patenddb')

const store = {}

store.save = (id, document) => {
  document._id = id
  return db.put(document)
    .then(() => {
      // console.log('store.save: Document saved, return it: ' + id + " [" + document + "]")
      return document
    })
    .catch(err => {
      if (err.name === 'conflict') {
        // console.error('store.save: Conflict, return current document', document)
        return document
      } else {
        // console.error('store.save: Error on save document: id -> ' + id + ', error: ' + err)
        return null
      }
    })
}

store.update = (currentDocument, newDocument) => {

  // console.log('store.update: Update document id: ' + currentDocument._id, currentDocument)

  newDocument._id = currentDocument._id
  newDocument._rev = currentDocument._rev

  return db.put(newDocument)
    .then(() => {
      // console.log('store.update: Document updated, return it: ' + newDocument._id, newDocument)
      return newDocument
    })
    .catch(err => {
      // console.error('store.update: Error on updated document: ' + err)
      return null
    })

}

store.findById = (id) => {
  // console.log('findById: ' + id)
  return db.get(id.toString())
    .then(response => {
      // console.info('Document ID ' + id + ' found', response)
      return response
    })
    .catch(err => {
      // console.error('Document ID ' + id + ' not found.', err)
      return null
    })
}

export default store
