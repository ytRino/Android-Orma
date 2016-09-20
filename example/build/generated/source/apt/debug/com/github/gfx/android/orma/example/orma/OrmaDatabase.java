package com.github.gfx.android.orma.example.orma;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.github.gfx.android.orma.DatabaseHandle;
import com.github.gfx.android.orma.Inserter;
import com.github.gfx.android.orma.ModelFactory;
import com.github.gfx.android.orma.OrmaConnection;
import com.github.gfx.android.orma.OrmaDatabaseBuilderBase;
import com.github.gfx.android.orma.Schema;
import com.github.gfx.android.orma.annotation.OnConflict;
import java.util.Arrays;
import java.util.List;
import rx.Completable;
import rx.Single;
import rx.SingleSubscriber;

/**
 * <p>The Orma database handle class.</p>
 * <p>To create instances: {@code OrmaDatabase.builder(context).build()}.</p>
 * <p>To delete databases: {@code context.deleteDatabase(OrmaDatabase.Builder.getDefaultDatabaseName(context))}</p>
 *
 * <p>This is generated by {@code com.github.gfx.android.orma.processor.OrmaProcessor}.</p>
 */
public class OrmaDatabase implements DatabaseHandle {
  /**
   * The SHA-256 digest of all the {@code CREATE TABLE} and {@code CREATE INDEX} statements.
   */
  public static String SCHEMA_HASH = "8A1796FDA0A2214ADDCC8F15088AAFAAC5E9B620DE4D55BFB8600989D244EE3A";

  public static final List<Schema<?>> SCHEMAS = Arrays.<Schema<?>>asList(
    Category_Schema.INSTANCE,
    Item_Schema.INSTANCE,
    Item2_Schema.INSTANCE,
    Todo_Schema.INSTANCE
  );

  private final OrmaConnection connection;

  public OrmaDatabase(@NonNull OrmaConnection connection) {
    this.connection = connection;
  }

  public static Builder builder(@NonNull Context context) {
    return new Builder(context);
  }

  @NonNull
  @Override
  public List<Schema<?>> getSchemas() {
    return SCHEMAS;
  }

  /**
   * <p>{@code migrate()} invokes database migration, which will takes several seconds.</p>
   * <p>This is completely optional and migration is invoked on the first access of the database, anyway.</p>
   *
   * @throws SQLiteConstraintException migration information is not sufficient.
   */
  public void migrate() throws SQLiteConstraintException {
    connection.getWritableDatabase();
  }

  @NonNull
  @Override
  public OrmaConnection getConnection() {
    return connection;
  }

  @WorkerThread
  public void transactionSync(@NonNull Runnable task) {
    connection.transactionSync(task);
  }

  @CheckResult
  public Completable transactionAsync(@NonNull Runnable task) {
    return connection.transactionAsync(task);
  }

  public void transactionNonExclusiveSync(@NonNull Runnable task) {
    connection.transactionNonExclusiveSync(task);
  }

  @CheckResult
  public Completable transactionNonExclusiveAsync(@NonNull Runnable task) {
    return connection.transactionNonExclusiveAsync(task);
  }

  /**
   * Retrieves a model from a cursor. */
  @NonNull
  public Category newCategoryFromCursor(@NonNull Cursor cursor) {
    return Category_Schema.INSTANCE.newModelFromCursor(connection, cursor, 0);
  }

  /**
   * Inserts a model created by {@code ModelFactory<T>}, and retrieves it which is just inserted.
   *  The return value has the row ID.
   */
  @NonNull
  @WorkerThread
  public Category createCategory(@NonNull ModelFactory<Category> factory) {
    return connection.createModel(Category_Schema.INSTANCE, factory);
  }

  /**
   * Creates a relation of {@code Category}, which is an entry point of all the operations.
   */
  @NonNull
  public Category_Relation relationOfCategory() {
    return new Category_Relation(connection, Category_Schema.INSTANCE);
  }

  /**
   * Starts building a query: {@code SELECT * FROM Category ...}.
   */
  @NonNull
  public Category_Selector selectFromCategory() {
    return new Category_Selector(connection, Category_Schema.INSTANCE);
  }

  /**
   * Starts building a query: {@code UPDATE Category ...}.
   */
  @WorkerThread
  @NonNull
  public Category_Updater updateCategory() {
    return new Category_Updater(connection, Category_Schema.INSTANCE);
  }

  /**
   * Starts building a query: {@code DELETE FROM Category ...}.
   */
  @WorkerThread
  @NonNull
  public Category_Deleter deleteFromCategory() {
    return new Category_Deleter(connection, Category_Schema.INSTANCE);
  }

  /**
   * Executes a query: {@code INSERT INTO Category ...}.
   */
  @WorkerThread
  public long insertIntoCategory(@NonNull Category model) {
    return prepareInsertIntoCategory().execute(model);
  }

  /**
   * Create a prepared statement for {@code INSERT INTO Category ...}.
   */
  @WorkerThread
  public Inserter<Category> prepareInsertIntoCategory() {
    return prepareInsertIntoCategory(OnConflict.NONE, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Category ...}.
   */
  @WorkerThread
  public Inserter<Category> prepareInsertIntoCategory(@OnConflict int onConflictAlgorithm) {
    return prepareInsertIntoCategory(onConflictAlgorithm, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Category ...}.
   */
  @WorkerThread
  public Inserter<Category> prepareInsertIntoCategory(@OnConflict int onConflictAlgorithm, boolean withoutAutoId) {
    return new Inserter<Category>(connection, Category_Schema.INSTANCE, onConflictAlgorithm, withoutAutoId);
  }

  /**
   * Create a prepared statement for {@code INSERT INTO Category ...}.
   */
  @CheckResult
  public Single<Inserter<Category>> prepareInsertIntoCategoryAsObservable() {
    return prepareInsertIntoCategoryAsObservable(OnConflict.NONE, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Category ...}.
   */
  @CheckResult
  public Single<Inserter<Category>> prepareInsertIntoCategoryAsObservable(@OnConflict int onConflictAlgorithm) {
    return prepareInsertIntoCategoryAsObservable(onConflictAlgorithm, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Category ...}.
   */
  @CheckResult
  public Single<Inserter<Category>> prepareInsertIntoCategoryAsObservable(@OnConflict final int onConflictAlgorithm, final boolean withoutAutoId) {
    return Single.create(new Single.OnSubscribe<Inserter<Category>>() {
      @Override
      public void call(SingleSubscriber<? super Inserter<Category>> subscriber) {
        subscriber.onSuccess(new Inserter<Category>(connection, Category_Schema.INSTANCE, onConflictAlgorithm, withoutAutoId));
      }
    });
  }

  /**
   * Retrieves a model from a cursor. */
  @NonNull
  public Item newItemFromCursor(@NonNull Cursor cursor) {
    return Item_Schema.INSTANCE.newModelFromCursor(connection, cursor, 0);
  }

  /**
   * Inserts a model created by {@code ModelFactory<T>}, and retrieves it which is just inserted.
   *  The return value has the row ID.
   */
  @NonNull
  @WorkerThread
  public Item createItem(@NonNull ModelFactory<Item> factory) {
    return connection.createModel(Item_Schema.INSTANCE, factory);
  }

  /**
   * Creates a relation of {@code Item}, which is an entry point of all the operations.
   */
  @NonNull
  public Item_Relation relationOfItem() {
    return new Item_Relation(connection, Item_Schema.INSTANCE);
  }

  /**
   * Starts building a query: {@code SELECT * FROM Item ...}.
   */
  @NonNull
  public Item_Selector selectFromItem() {
    return new Item_Selector(connection, Item_Schema.INSTANCE);
  }

  /**
   * Starts building a query: {@code UPDATE Item ...}.
   */
  @WorkerThread
  @NonNull
  public Item_Updater updateItem() {
    return new Item_Updater(connection, Item_Schema.INSTANCE);
  }

  /**
   * Starts building a query: {@code DELETE FROM Item ...}.
   */
  @WorkerThread
  @NonNull
  public Item_Deleter deleteFromItem() {
    return new Item_Deleter(connection, Item_Schema.INSTANCE);
  }

  /**
   * Executes a query: {@code INSERT INTO Item ...}.
   */
  @WorkerThread
  public long insertIntoItem(@NonNull Item model) {
    return prepareInsertIntoItem().execute(model);
  }

  /**
   * Create a prepared statement for {@code INSERT INTO Item ...}.
   */
  @WorkerThread
  public Inserter<Item> prepareInsertIntoItem() {
    return prepareInsertIntoItem(OnConflict.NONE, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Item ...}.
   */
  @WorkerThread
  public Inserter<Item> prepareInsertIntoItem(@OnConflict int onConflictAlgorithm) {
    return prepareInsertIntoItem(onConflictAlgorithm, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Item ...}.
   */
  @WorkerThread
  public Inserter<Item> prepareInsertIntoItem(@OnConflict int onConflictAlgorithm, boolean withoutAutoId) {
    return new Inserter<Item>(connection, Item_Schema.INSTANCE, onConflictAlgorithm, withoutAutoId);
  }

  /**
   * Create a prepared statement for {@code INSERT INTO Item ...}.
   */
  @CheckResult
  public Single<Inserter<Item>> prepareInsertIntoItemAsObservable() {
    return prepareInsertIntoItemAsObservable(OnConflict.NONE, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Item ...}.
   */
  @CheckResult
  public Single<Inserter<Item>> prepareInsertIntoItemAsObservable(@OnConflict int onConflictAlgorithm) {
    return prepareInsertIntoItemAsObservable(onConflictAlgorithm, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Item ...}.
   */
  @CheckResult
  public Single<Inserter<Item>> prepareInsertIntoItemAsObservable(@OnConflict final int onConflictAlgorithm, final boolean withoutAutoId) {
    return Single.create(new Single.OnSubscribe<Inserter<Item>>() {
      @Override
      public void call(SingleSubscriber<? super Inserter<Item>> subscriber) {
        subscriber.onSuccess(new Inserter<Item>(connection, Item_Schema.INSTANCE, onConflictAlgorithm, withoutAutoId));
      }
    });
  }

  /**
   * Retrieves a model from a cursor. */
  @NonNull
  public Item2 newItem2FromCursor(@NonNull Cursor cursor) {
    return Item2_Schema.INSTANCE.newModelFromCursor(connection, cursor, 0);
  }

  /**
   * Inserts a model created by {@code ModelFactory<T>}, and retrieves it which is just inserted.
   *  The return value has the row ID.
   */
  @NonNull
  @WorkerThread
  public Item2 createItem2(@NonNull ModelFactory<Item2> factory) {
    return connection.createModel(Item2_Schema.INSTANCE, factory);
  }

  /**
   * Creates a relation of {@code Item2}, which is an entry point of all the operations.
   */
  @NonNull
  public Item2_Relation relationOfItem2() {
    return new Item2_Relation(connection, Item2_Schema.INSTANCE);
  }

  /**
   * Starts building a query: {@code SELECT * FROM Item2 ...}.
   */
  @NonNull
  public Item2_Selector selectFromItem2() {
    return new Item2_Selector(connection, Item2_Schema.INSTANCE);
  }

  /**
   * Starts building a query: {@code UPDATE Item2 ...}.
   */
  @WorkerThread
  @NonNull
  public Item2_Updater updateItem2() {
    return new Item2_Updater(connection, Item2_Schema.INSTANCE);
  }

  /**
   * Starts building a query: {@code DELETE FROM Item2 ...}.
   */
  @WorkerThread
  @NonNull
  public Item2_Deleter deleteFromItem2() {
    return new Item2_Deleter(connection, Item2_Schema.INSTANCE);
  }

  /**
   * Executes a query: {@code INSERT INTO Item2 ...}.
   */
  @WorkerThread
  public long insertIntoItem2(@NonNull Item2 model) {
    return prepareInsertIntoItem2().execute(model);
  }

  /**
   * Create a prepared statement for {@code INSERT INTO Item2 ...}.
   */
  @WorkerThread
  public Inserter<Item2> prepareInsertIntoItem2() {
    return prepareInsertIntoItem2(OnConflict.NONE, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Item2 ...}.
   */
  @WorkerThread
  public Inserter<Item2> prepareInsertIntoItem2(@OnConflict int onConflictAlgorithm) {
    return prepareInsertIntoItem2(onConflictAlgorithm, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Item2 ...}.
   */
  @WorkerThread
  public Inserter<Item2> prepareInsertIntoItem2(@OnConflict int onConflictAlgorithm, boolean withoutAutoId) {
    return new Inserter<Item2>(connection, Item2_Schema.INSTANCE, onConflictAlgorithm, withoutAutoId);
  }

  /**
   * Create a prepared statement for {@code INSERT INTO Item2 ...}.
   */
  @CheckResult
  public Single<Inserter<Item2>> prepareInsertIntoItem2AsObservable() {
    return prepareInsertIntoItem2AsObservable(OnConflict.NONE, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Item2 ...}.
   */
  @CheckResult
  public Single<Inserter<Item2>> prepareInsertIntoItem2AsObservable(@OnConflict int onConflictAlgorithm) {
    return prepareInsertIntoItem2AsObservable(onConflictAlgorithm, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Item2 ...}.
   */
  @CheckResult
  public Single<Inserter<Item2>> prepareInsertIntoItem2AsObservable(@OnConflict final int onConflictAlgorithm, final boolean withoutAutoId) {
    return Single.create(new Single.OnSubscribe<Inserter<Item2>>() {
      @Override
      public void call(SingleSubscriber<? super Inserter<Item2>> subscriber) {
        subscriber.onSuccess(new Inserter<Item2>(connection, Item2_Schema.INSTANCE, onConflictAlgorithm, withoutAutoId));
      }
    });
  }

  /**
   * Retrieves a model from a cursor. */
  @NonNull
  public Todo newTodoFromCursor(@NonNull Cursor cursor) {
    return Todo_Schema.INSTANCE.newModelFromCursor(connection, cursor, 0);
  }

  /**
   * Inserts a model created by {@code ModelFactory<T>}, and retrieves it which is just inserted.
   *  The return value has the row ID.
   */
  @NonNull
  @WorkerThread
  public Todo createTodo(@NonNull ModelFactory<Todo> factory) {
    return connection.createModel(Todo_Schema.INSTANCE, factory);
  }

  /**
   * Creates a relation of {@code Todo}, which is an entry point of all the operations.
   */
  @NonNull
  public Todo_Relation relationOfTodo() {
    return new Todo_Relation(connection, Todo_Schema.INSTANCE);
  }

  /**
   * Starts building a query: {@code SELECT * FROM Todo ...}.
   */
  @NonNull
  public Todo_Selector selectFromTodo() {
    return new Todo_Selector(connection, Todo_Schema.INSTANCE);
  }

  /**
   * Starts building a query: {@code UPDATE Todo ...}.
   */
  @WorkerThread
  @NonNull
  public Todo_Updater updateTodo() {
    return new Todo_Updater(connection, Todo_Schema.INSTANCE);
  }

  /**
   * Starts building a query: {@code DELETE FROM Todo ...}.
   */
  @WorkerThread
  @NonNull
  public Todo_Deleter deleteFromTodo() {
    return new Todo_Deleter(connection, Todo_Schema.INSTANCE);
  }

  /**
   * Executes a query: {@code INSERT INTO Todo ...}.
   */
  @WorkerThread
  public long insertIntoTodo(@NonNull Todo model) {
    return prepareInsertIntoTodo().execute(model);
  }

  /**
   * Create a prepared statement for {@code INSERT INTO Todo ...}.
   */
  @WorkerThread
  public Inserter<Todo> prepareInsertIntoTodo() {
    return prepareInsertIntoTodo(OnConflict.NONE, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Todo ...}.
   */
  @WorkerThread
  public Inserter<Todo> prepareInsertIntoTodo(@OnConflict int onConflictAlgorithm) {
    return prepareInsertIntoTodo(onConflictAlgorithm, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Todo ...}.
   */
  @WorkerThread
  public Inserter<Todo> prepareInsertIntoTodo(@OnConflict int onConflictAlgorithm, boolean withoutAutoId) {
    return new Inserter<Todo>(connection, Todo_Schema.INSTANCE, onConflictAlgorithm, withoutAutoId);
  }

  /**
   * Create a prepared statement for {@code INSERT INTO Todo ...}.
   */
  @CheckResult
  public Single<Inserter<Todo>> prepareInsertIntoTodoAsObservable() {
    return prepareInsertIntoTodoAsObservable(OnConflict.NONE, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Todo ...}.
   */
  @CheckResult
  public Single<Inserter<Todo>> prepareInsertIntoTodoAsObservable(@OnConflict int onConflictAlgorithm) {
    return prepareInsertIntoTodoAsObservable(onConflictAlgorithm, true);
  }

  /**
   * Create a prepared statement for {@code INSERT OR ... INTO Todo ...}.
   */
  @CheckResult
  public Single<Inserter<Todo>> prepareInsertIntoTodoAsObservable(@OnConflict final int onConflictAlgorithm, final boolean withoutAutoId) {
    return Single.create(new Single.OnSubscribe<Inserter<Todo>>() {
      @Override
      public void call(SingleSubscriber<? super Inserter<Todo>> subscriber) {
        subscriber.onSuccess(new Inserter<Todo>(connection, Todo_Schema.INSTANCE, onConflictAlgorithm, withoutAutoId));
      }
    });
  }

  public static class Builder extends OrmaDatabaseBuilderBase<Builder> {
    public Builder(@NonNull Context context) {
      super(context);
    }

    @NonNull
    @Override
    protected String getSchemaHash() {
      return SCHEMA_HASH;
    }

    public OrmaDatabase build() {
      return new OrmaDatabase(new OrmaConnection(fillDefaults(), SCHEMAS));
    }
  }
}
